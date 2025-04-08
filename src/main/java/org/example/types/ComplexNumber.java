package org.example.types;

public record ComplexNumber(double real, double imaginary) implements Comparable<ComplexNumber> {

    @Override
    public int compareTo(ComplexNumber other) {
        double thisMagnitude = Math.sqrt(this.real * real + this.imaginary * imaginary);
        double otherMagnitude = Math.sqrt(other.real * other.real + other.imaginary * other.imaginary);
        return Double.compare(thisMagnitude, otherMagnitude);
    }

    @Override
    public String toString() {
        return real + " + " + imaginary + "i";
    }
}