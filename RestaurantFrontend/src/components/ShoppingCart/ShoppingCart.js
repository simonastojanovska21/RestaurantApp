import React from "react";
import {Link, Redirect} from "react-router-dom";
import MealInShoppingCart from "./MealInShoppingCart";

const shoppingCart=(props)=>{
    const calculateSubtotal=(price,quantity)=>{
        return Math.round((price*quantity)*100.0)/100.0;
    }
    const calculateTotal=(items)=>{
        let arr=[];
        items.forEach(each=>arr.push(each.orderItemForMeal.price*each.quantity))
        return arr.reduce((a,b)=>a+b,0);
    }

    if (localStorage.getItem("user")===null)
    {
        return (
            <Redirect to={"/unauthorized"} />
        )
    }

    return(
        <div className="container mt-5 mb-5" id={"shoppingCart"} >
            {props.orderedItems.length!==0 &&
            <div className="row" >
                <span className={"title text-center mb-5"}>My shopping cart</span>
                <div className="col-md-12 ">
                    <div>
                        <div className={"row pb-3 greenBackground"}>
                            <div className={"col-md-4 fs-5 fw-bold goldText text-center pt-3"}>
                                Meal
                            </div>
                            <div className={"col-md-2  fs-5 fw-bold goldText pt-3"}>
                                Price
                            </div>
                            <div className={"col-md-4 text-center fs-5 fw-bold goldText pt-3"}>
                                Quantity
                            </div>
                            <div className={"col-md-2 fs-5 fw-bold goldText pt-3"}>
                                Subtotal
                            </div>
                        </div>
                    </div>
                    {props.orderedItems.map((term)=>{
                        return(
                            <div className={"itemInShoppingCartRow"}>
                                <div className={" row border pt-3 pb-3"}>
                                    <div className={"col-md-4 d-flex align-items-center"}>
                                        <Link className={"btn btn-sm me-3 deleteOrderItem-"+term.id} onClick={()=>props.onDeleteItemFromOrder(term.id)} >
                                            <i className="fa fa-trash"/>
                                        </Link>
                                        <img src={term.orderItemForMeal.imageUrl} className={"shoppingCartImage"} alt={"meal"}/>
                                        <span className={"mealName mealInShoppingCartName-"+term.orderItemForMeal.name}>{term.orderItemForMeal.name}</span>
                                    </div>

                                    <div className={"col-md-2 d-flex align-items-center mealPrice mealInShoppingCartPrice-"+term.orderItemForMeal.name}>
                                        {term.orderItemForMeal.price} $
                                    </div>

                                    <div className={"col-md-4 d-flex align-items-center mt-3 mealInShoppingCartQuantity-"+term.orderItemForMeal.name}>
                                        <MealInShoppingCart id={term.itemInOrder.id} quantity={term.quantity}
                                                            itemId={term.id}
                                                            onPlusOrderItemFromOrder={props.onPlusOrderItemFromOrder}
                                                            onMinusOrderItemFromOrder={props.onMinusOrderItemFromOrder} />
                                    </div>

                                    <div className={"col-md-2 d-flex align-items-center fw-bold mealSubtotal mealInShoppingCartSubtotal-"+term.orderItemForMeal.name}>
                                        {calculateSubtotal(term.orderItemForMeal.price,term.quantity)} $
                                    </div>

                                </div>

                            </div>

                        )
                    })}
                    <div>
                        <div className={"row pb-3 greenBackground"}>

                            <div className={"col-md-10 text-end fs-5 fw-bold goldText pt-3"}>
                                Total:
                            </div>
                            <div className={"col-md-2 fs-5 fw-bold goldText pt-3"} id={"orderTotal"}>
                                {calculateTotal(props.orderedItems)}
                            </div>
                        </div>
                    </div>
                </div>
                <div className={"row mt-5"}>
                    <div className={"col"}>
                        <Link className={"btn w-100 fw-bold"} id={"continueShopping"} to={"/menu"} style={{backgroundColor: '#bb9c57', color:'#004332'}} >
                            Continue shopping
                        </Link>
                    </div>

                    <div className={"col"}>
                        <Link className={"btn w-100 fw-bold"} id={"checkOut"} to={"/checkOut"} style={{backgroundColor: '#bb9c57', color:'#004332'}} >
                            Check out
                        </Link>
                    </div>
                </div>
            </div>

            }



            {props.orderedItems.length===0 &&
            <div id={"emptyShoppingCart"}>
                <div className="row text-center" >
                     <span className={"title text-center mb-5"}>
                        Your shopping cart is empty
                    </span>

                </div>
                <br/>
                <br/>
                <div className="row text-center">
                    <Link className={"btn w-100 fw-bold"} id={"backToMenu"} to={"/menu"} style={{backgroundColor: '#004332', color:'#bb9c57'}}>
                        Back to menu page
                    </Link>
                </div>
            </div>
            }
        </div>
    )

}

export default shoppingCart;