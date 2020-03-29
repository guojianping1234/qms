package simpleFactory

class Sub implements Operation {
    @Override
    double getResut(double numA, double numB) {
        return numA - numB;
    }
}
