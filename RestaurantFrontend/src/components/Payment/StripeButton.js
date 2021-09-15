import React from "react";
import StripeCheckout from "react-stripe-checkout";
import axios from "../../custom-axios/axios";
import {useHistory} from "react-router-dom";

const StripeButton=(props)=>{
    const publishableKey = "pk_test_51J7SbnEZTDyznlQRAKwy6Vj2vMMWNsbns1OE7SkaVfKbOHU2Q1S5JIXvzz100kAvJHdxio1Or7G3Dyd4uJpjB3jD0070T275uD";
    const stripePrice = props.price * 100;
    const history = useHistory();

    const onToken= /* istanbul ignore next */ (token)=>{
        console.log(props.orderId)
        return axios.post('/api/payment',{
            "amount":stripePrice,
            "token":token,
            //"orderId":props.orderId
            "username":props.username
        })
        .then((response) => {
            console.log(response);
            //alert("payment success");
            history.push("/profile");
        })
        .catch((error) => {
        console.log(error);
        //alert("Payment failed");
        });
    }

    return (
        <StripeCheckout
            id={"checkOutButton"}
            amount={stripePrice}
            label="Pay Now"
            name="My restaurant"
            image="https://svgshare.com/i/CUz.svg"
            description={`Your total is ${props.price}`}
            panelLabel="Pay Now"
            token={onToken}
            stripeKey={publishableKey}
            currency="USD"
        />
    );
}

export default StripeButton;