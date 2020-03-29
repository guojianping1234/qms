package simpleFactory

class EasyFactory {

    // 简单工厂，根据字符串创建相应的对
    public static Operation createOpration(String name) {
        Operation operation;
        switch (name) {
            case "+": operation = new Add(); break
            case "-": operation = new Sub(); break
            default: operation = null;

        }
        return operation
    }
}
