package transaction.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import transaction.Model.BadResponse;
import transaction.Model.TransactionBean;
import transaction.dao.TransactionLedger;

@Validated
@RestController
@RequestMapping("/api/v1")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class TransactionController {

	TransactionLedger transactionLedger;

	public TransactionController() {
		transactionLedger = new TransactionLedger();
	}

	@RequestMapping(value = "/transactions", 
			produces = { MediaType.APPLICATION_JSON_VALUE }, 
			consumes = {
			MediaType.APPLICATION_JSON_VALUE }, 
			method = RequestMethod.POST)
	public ResponseEntity<String> saveTransaction(@Valid @RequestBody List<TransactionBean> transactionBean) {
		try {
			transactionLedger.save(transactionBean);

		} catch (Exception e) {
			BadResponse badResponse = new BadResponse();
			badResponse.buildUnprocesableEntity(e.getLocalizedMessage());
			System.out.println("Bad Request: " + badResponse.toString());
			String jsonStr = new Gson().toJson(badResponse);
			return new ResponseEntity<String>(jsonStr, HttpStatus.UNPROCESSABLE_ENTITY);
			
		}

		return new ResponseEntity<String>("Successfully Inserted", HttpStatus.OK);
	}

	@RequestMapping(value = "/transactions", 
			produces = { MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_JSON_VALUE }, 
			method = RequestMethod.GET)
	public ResponseEntity<String> getTransactions(@Valid @RequestBody List<TransactionBean> transactionBean) {
		String jsonStr = null;
		List<TransactionBean> response = null;


		try {


			response = transactionLedger.getTransaction(transactionBean);
			jsonStr = new Gson().toJson(response);

		//} catch (JsonProcessingException e) {
		} catch (Exception e) {
			BadResponse badResponse = new BadResponse();
			badResponse.buildUnprocesableEntity(e.getLocalizedMessage());
			System.out.println("Bad Request: " + badResponse.toString());
			String badJsonResponse = new Gson().toJson(badResponse);
			return new ResponseEntity<String>(badJsonResponse, HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return new ResponseEntity<String>(jsonStr, HttpStatus.OK);
	}
}
