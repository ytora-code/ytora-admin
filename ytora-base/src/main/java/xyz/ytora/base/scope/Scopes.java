package xyz.ytora.base.scope;

import xyz.ytora.base.exception.BaseException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

/**
 * ScopedValue上下文相关工具类
 *
 * @author ytora
 * @since 1.0
 */
public final class Scopes {

    List<ScopedValuePair<?>> pairList = new ArrayList<>();

    public static <T> Scopes start() {
        return new Scopes();
    }

    public <T> Scopes where(ScopedValue<T> key, T value) {
        this.pairList.add(new ScopedValuePair<T>(key, value));
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> T run(Supplier<T> supplier) throws Throwable {
        AtomicReference<T> resultRef = new AtomicReference<>();
        AtomicReference<Throwable> exceptionRef = new AtomicReference<>();

        ScopedValue.Carrier carrier = ScopedValue.where(ScopedValueContext.NULL_CONTEXT, null);
        for (ScopedValuePair<?> pair : pairList) {
            carrier = carrier.where((ScopedValue<Object>) pair.key(), pair.value());
        }
        carrier.run(() -> {
            try {
                T t = supplier.get();
                resultRef.set(t);
            } catch (Throwable e) {
                exceptionRef.set(e);
            }
        });

        if (exceptionRef.get() != null) {
            throw exceptionRef.get();
        }
        return resultRef.get();
    }

}
