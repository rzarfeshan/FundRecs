package transaction.Model;

import java.text.DecimalFormat;
import java.time.format.DateTimeParseException;

import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import transaction.Model.util.DateValidatorDateTimeFormatter;

public class TransactionBean implements Comparable<TransactionBean> {
	
	@NotNull(message = "Transaction Date must not be empty and should be in valid date format i.e. dd-mm-yyyy")
	String date;
	
	@NotNull(message = "Transaction type must not be empty")
	String type;
	
	Double amount;

	public String getDate() {
		return date;
	}

	public void setDate(String date) throws DateTimeParseException {
		//check if date is valid or not
		if (new DateValidatorDateTimeFormatter().isValid(date))
			this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = Double.parseDouble(new DecimalFormat("#0.##").format(amount));
	}

	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionBean other = (TransactionBean) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
*/
	@Override
	public String toString() {
		return "TransactionBean [date=" + date + ", type=" + type + ", amount=" + amount + "]";
	}

	@Override
	public int compareTo(TransactionBean o) {
		if (this.date.compareTo(o.date) == 0) {
			return this.type.compareTo(o.type);
		}
		return this.date.compareTo(o.date);
	}
	
	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
	     return new MethodValidationPostProcessor();
	}

}
