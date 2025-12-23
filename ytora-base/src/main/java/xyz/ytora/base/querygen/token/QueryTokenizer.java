package xyz.ytora.base.querygen.token;

/**
 * 查询参数分词器，用于解析类似 "age!=1" 或 "name_or=1,23" 的字符串
 */
public class QueryTokenizer {

    /**
     * 分词方法
     *
     * @param input 查询参数字符串，例如 "age!=1" 或 "name_or=1,23"
     * @return Token，包含字段名、正向标志、值
     * @throws IllegalArgumentException 如果输入格式不符合预期
     */
    public static Token tokenize(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("输入不能为空");
        }
        // 优先匹配 "!="，否则再匹配 "="
        int index = input.indexOf("!=");
        boolean positive;
        String field;
        String value;
        if (index != -1) {
            positive = false;
            field = input.substring(0, index);
            value = input.substring(index + 2); // 跳过 "!="
        } else {
            index = input.indexOf("=");
            if (index != -1) {
                positive = true;
                field = input.substring(0, index);
                value = input.substring(index + 1);
            } else {
                throw new IllegalArgumentException("输入格式不正确，必须包含 '=' 或 '!='");
            }
        }
        // 修剪空白字符
        field = field.trim();
        value = value.trim();
        return new Token(field, positive, value);
    }

    // 测试入口
    static void main(String[] args) {
        String test1 = "age!=1";
        String test2 = "name_or=1,23";

        Token token1 = tokenize(test1);
        Token token2 = tokenize(test2);

        System.out.println(token1); // 输出: (age, false, 1)
        System.out.println(token2); // 输出: (name_or, true, 1,23)
    }
}