import axios from "../custom-axios/axios";
/* istanbul ignore next */
const PaymentService={
    payAmount:(amount,token)=>{
        return axios.post('/api/payment',{
            "amount":amount,
            "token":token
        })
            .then((response) => {
                console.log(response);
                alert("payment success");
            })
            .catch((error) => {
                console.log(error);
                alert("Payment failed");
            });
    }
}

export default PaymentService;