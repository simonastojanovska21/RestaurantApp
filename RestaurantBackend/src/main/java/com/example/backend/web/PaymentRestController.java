package com.example.backend.web;

import com.example.backend.model.exceptions.OrderNotFound;
import com.example.backend.model.exceptions.UserCanNotHave2ActiveOrders;
import com.example.backend.model.exceptions.UserNotFoundException;
import com.example.backend.model.payment.PaymentRequest;
import com.example.backend.service.OrderService;
import com.example.backend.service.impl.PaymentService;
import com.stripe.exception.StripeException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
@RequestMapping("/api/payment")
public class PaymentRestController {

    private PaymentService service;
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<String> completePayment(@RequestBody PaymentRequest request) throws StripeException {
        try {
            String chargeId= service.charge(request);
            if(chargeId!= null)
            {
                //this.orderService.payForOrder(request.getOrderId());
                this.orderService.payForUserOrder(request.getUsername());
                return new ResponseEntity<String>(chargeId, HttpStatus.OK);
            }
            return new ResponseEntity<String>("Please check the credit card details entered",HttpStatus.BAD_REQUEST);
        }
        catch (UserNotFoundException | UserCanNotHave2ActiveOrders | OrderNotFound e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }

    @ExceptionHandler
    public String handleError(StripeException ex) {
        return ex.getMessage();
    }
}
