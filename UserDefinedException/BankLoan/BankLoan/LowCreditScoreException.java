package BankLoan;
public class LowCreditScoreException extends Exception {
    LowCreditScoreException(String message){
        super(message);
    }
}
