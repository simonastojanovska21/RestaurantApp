import React from "react";
import {Link, Redirect} from "react-router-dom";
import StripeButton from "../Payment/StripeButton"

const checkOutOrder=(props)=>{
    const calculateTotal=(items)=>{
        let arr=[];
        items.forEach(each=>arr.push(each.orderItemForMeal.price*each.quantity))
        return arr.reduce((a,b)=>a+b,0);
    }
    const totalWithDelivery=()=>{
        let total=calculateTotal(props.orderedItems)
        return total >= 100 ? total : total+20;
    }

    if (localStorage.getItem("user")===null)
    {
        return (
            <Redirect to={"/unauthorized"} />
        )
    }
//console.log(props.username)

    return(
        <div className="container mt-5 mb-5" >
            <div className="row" >
                <span className={"title text-center mb-5"}>Order summary</span>
                <div className={"col-md-8 ms-5"}>
                    <div className={"row"}>
                        <div className={"col fs-2 border greenBackground goldText"}>
                            Total:
                        </div>
                        <div className={"col fs-2 border"} id={"totalPrice"}>
                            {calculateTotal(props.orderedItems)} $
                        </div>
                    </div>

                    <div className={"row "}>
                        <div className={"col border greenBackground goldText"}>
                            <span className={"fs-2"}>Delivery fee:</span> <br/>
                            <span className={"form-text"}>If your total is less than $100</span>
                        </div>
                        <div className={"col d-flex align-items-center  fs-2 border"} id={"deliveryFee"}>
                            {calculateTotal(props.orderedItems) < 100 &&
                                <span>20 $</span>
                            }
                            {calculateTotal(props.orderedItems) >= 100 &&
                            <span>0 $</span>
                            }
                        </div>
                    </div>

                    <div className={"row"}>
                        <div className={"col fs-2 border greenBackground goldText"}>
                            Total with delivery:
                        </div>
                        <div className={"col fs-2 border"} id={"totalWithDelivery"}>
                            {totalWithDelivery()} $
                        </div>
                    </div>

                </div>
            </div>

            <div className={"row mt-5"}>
                <div className={"col"}>
                    <Link className={"btn w-100 fw-bold"} id={"cancelOrder"} onClick={()=>props.cancelOrder(props.username)}
                          to={"/menu"} style={{backgroundColor: '#bb9c57', color:'#004332'}} >
                        Cancel order
                    </Link>
                </div>

                <div className={"col"}>
                    {/*<StripeButton className={"btn w-100 fw-bold"} price={totalWithDelivery()} orderId={props.orderId} />*/}
                    <StripeButton id={"checkOutButton"} price={totalWithDelivery()} username={props.username} />
                </div>
            </div>

        </div>
    )
}

export default checkOutOrder;