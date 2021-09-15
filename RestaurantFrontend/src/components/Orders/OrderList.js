import React, {Component} from "react";
import {Redirect} from "react-router-dom";
import OrderTerm from "../Orders/OrderTerm"

const OrderList=(props)=>{

    /* istanbul ignore else */
    if (localStorage.getItem("userRole")===null)
    {
        return (
            <Redirect to={"/unauthorized"} />
        )
    }
    /* istanbul ignore else */
    if( ! (localStorage.getItem("userRole").includes("ADMIN") || localStorage.getItem("userRole").includes("EMPLOYEE") ) )
    {
        window.location.assign("http://localhost:3000/unauthorized")
    }

    return(
        <div className={"container mt-5 pb-5 text-center"}>
            <span className={"title"}>List of orders for preparing</span>

            <div className={"row"}>
                <div className={"table-responsive"}>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col-md-3"}>Customer name</th>
                            <th scope={"col-md-3"}>Meals</th>
                            <th scope={"col-md-3"}>Delivery address</th>
                            <th scope={"col-md-3"}>Ready for delivery</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.orders.map((term)=>{
                            return(
                                <tr className={"orderRow"}>
                                    <td className={"align-middle customerName"}>
                                        {term.orderedByUser.username}
                                    </td>
                                    <OrderTerm order={term} />
                                    <td className={"deliveryAddress"}>
                                        {term.orderedByUser.address}
                                    </td>

                                    <td className={"readyForDeliveryButton"}>
                                        <button className={"btn w-100 fw-bold readyForDelivery-"+term.id}
                                                onClick={()=>props.onReadyForDelivery(term.orderedByUser.address,term.id)}
                                                style={{backgroundColor: '#004332', color:'#bb9c57'}}>
                                            Ready for delivery
                                        </button>
                                    </td>
                                </tr>
                            )
                        })}
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    )
}

export default OrderList;