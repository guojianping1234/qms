package simpleFactory

class TestSimplateFactory {
    public static void main(String[] args) {
        EasyFactory es = new EasyFactory();
        Operation add = es.createOpration("+")
        Operation sub = es.createOpration("-")

        println(add.getResut(0.5, 0.6))
        println(sub.getResut(0.6, 0.5))

    }

}
