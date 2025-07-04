public class customer
{
    double balance;

    public customer(double initialBalance) 
    {
        this.balance = initialBalance;
    }

    public double getBalance() 
    {
        return balance;
    }

    public boolean checkBalance(double amount) 
    {
        return balance >= amount;
    }

    public void deductBalance(double amount) throws Exception
    {
        if (amount > balance) {
            throw new Exception("Insufficient balance");
        }
        balance -= amount;
    }
}