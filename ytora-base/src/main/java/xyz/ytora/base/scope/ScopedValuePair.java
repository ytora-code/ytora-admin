package xyz.ytora.base.scope;

/**
 * 一对ScopedValue键值对
 *
 * @author ytora 
 * @since 1.0
 */
public record ScopedValuePair<T>(ScopedValue<T> key, T value) {
}
