package modelo.dto;

import java.util.Objects;

public class Cuenta implements Comparable<Cuenta> {
    private int id;
    private String iban;
    private Long creditCard;
    private double balance;
    private String fullName;
    private String date;

    public Cuenta(int id, String iban, Long creditCard, double balance, String fullName, String date) {
        this.id = id;
        this.iban = iban;
        this.creditCard = creditCard;
        this.balance = balance;
        this.fullName = fullName;
        this.date = date;
    }

    public Cuenta(String iban, Long creditCard, double balance, String fullName, String date) {
        this.iban = iban;
        this.creditCard = creditCard;
        this.balance = balance;
        this.fullName = fullName;
        this.date = date;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Long getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(Long creditCard) {
        this.creditCard = creditCard;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%d ; %s ; %d ; %.2f ; %s ; %s", id, iban, creditCard,
                balance, fullName, date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuenta cuenta = (Cuenta) o;
        return Objects.equals(iban, cuenta.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iban);
    }

    @Override
    public int compareTo(Cuenta cuenta) {
        return (int) (this.balance *100 - cuenta.balance * 100);
    }
}
