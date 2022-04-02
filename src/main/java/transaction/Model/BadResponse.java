package transaction.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;

public class BadResponse {
	private String timestamp;

	private String status;

	private String error;
	
	private String message;
	
//	public BadResponse(String badResponse, String details) {
//		this.message = details;
//		this.error = badResponse;
//		this.status = HttpStatus.BAD_REQUEST.toString();
//		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//		this.timestamp = formatter.format(timestamp);
//	}

	@Override
	public String toString() {
		return "BadResponse [timestamp=" + timestamp + ", status=" + status + ", error=" + error + ", message="
				+ message + "]";
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		this.timestamp = formatter.format(timestamp);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BadResponse buildUnprocesableEntity(String message) {
		this.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.toString());
		this.setError(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase());
		this.setMessage(message);
		this.setTimestamp(new Date());
		return this;
	}

	public BadResponse buildBadRequest(String message) {
		this.setStatus(HttpStatus.BAD_REQUEST.toString());
		this.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
		this.setMessage(message);
		this.setTimestamp(new Date());
		return this;
	}
}
