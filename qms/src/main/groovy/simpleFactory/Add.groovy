package simpleFactory

class Add implements Operation {
    @Override
    double getResut(double numA, double numB) {
        return numA + numB;
    }
}
