public interface Financeble {
    double checkBalance();
    boolean hasEnoughMoney(double amount);
    FinanceStatus getFinanceStatus();

    enum FinanceStatus {
        SOLVENT,    // платежеспособен (баланс >= 1000)
        INSOLVENT,  // неплатежеспособен (баланс <= 0)
        LIMITED     // ограничен в средствах (0 < баланс < 1000)
    }
}