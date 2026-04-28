import java.util.Objects;

public class Person implements Payable, Financeble {
    private String name;
    private String surname;
    private int bornDate;
    private int id;
    private double balance;
    private static int counter = 0;

    public Person(String name, String surname, int bornDate) {
        this.name = name;
        this.surname = surname;
        this.bornDate = bornDate;
        this.id = ++counter;
        this.balance = 0.0;
    }

    public Person(String name, String surname, int bornDate, double initialBalance) {
        this(name, surname, bornDate);
        this.balance = initialBalance;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getBornDate() {
        return bornDate;
    }

    public int getId() {
        return id;
    }

    // Реализация Payable
    @Override
    public boolean pay(double amount) {
        if (amount <= 0) {
            System.out.println("Сумма платежа должна быть положительной");
            return false;
        }

        if (balance >= amount) {
            balance -= amount;
            System.out.println("Платеж на сумму " + amount + " успешно выполнен");
            return true;
        } else {
            System.out.println("Недостаточно средств. Доступно: " + balance);
            return false;
        }
    }

    @Override
    public double getBalance() {
        return balance;
    }

    // Реализация Financeble
    @Override
    public double checkBalance() {
        return balance;
    }

    @Override
    public boolean hasEnoughMoney(double amount) {
        return balance >= amount;
    }

    @Override
    public FinanceStatus getFinanceStatus() {
        if (balance <= 0) {
            return FinanceStatus.INSOLVENT;
        } else if (balance < 1000) {
            return FinanceStatus.LIMITED;
        } else {
            return FinanceStatus.SOLVENT;
        }
    }

    // Пополнение баланса
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Баланс пополнен на " + amount + ". Текущий баланс: " + balance);
        } else {
            System.out.println("Сумма пополнения должна быть положительной");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                bornDate == person.bornDate &&
                Double.compare(person.balance, balance) == 0 && // Исправлено: Double.compare
                Objects.equals(name, person.name) &&
                Objects.equals(surname, person.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, bornDate, id, balance);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", bornDate=" + bornDate +
                ", id=" + id +
                ", balance=" + balance +
                ", financeStatus=" + getFinanceStatus() +
                '}';
    }
}