import React from "react";
import AddMenuItemInOrder from "./AddMenuItemInOrder";
import MenuItem from "../Menu/MenuItem"

const mealDetails=(props)=>{
    return(
        <div className={"container mt-5 mb-5"}>
            <h1 className={"title text-center mt-3 mb-5"}>Details for meal</h1>
            <div className={"row"}>
                <div className={"col-md-5"}>
                    <img src={props.selectedMeal.imageUrl} className={"gridImages ms-5 mealDetailsImage-"+ props.selectedMeal.id} alt={"meal"} />
                </div>
                <div className={"col-md-7"}>

                        <h1 className={"greenText mealDetailsName-"+ props.selectedMeal.id}>{props.selectedMeal.name}</h1>
                        <hr/>
                        <h3 className={"mealDetailsDescription-"+ props.selectedMeal.id}>{props.selectedMeal.description}</h3>
                        <h1 className={"brownText mealDetailsPrice-"+ props.selectedMeal.id}>{props.selectedMeal.price} $</h1>
                        <h4 className={"greenText fw-bold"}>Ingredients:</h4>
                        <ul className={"dashedList"}>
                            {/* eslint-disable-next-line array-callback-return */}
                            {props.ingredients.map((term)=>{
                                if(props.selectedMeal.ingredientsForMeal !==undefined && props.selectedMeal.ingredientsForMeal.map(each=>each.id).includes(term.id))
                                    return(
                                        <li className={"mealDetailsIngredients-"+ props.selectedMeal.id} >{ term.name }</li>
                                    )

                            })}
                        </ul>
                    <div className={"mt-5 d-flex justify-content-start"}>
                        {localStorage.getItem("user") !==null &&
                            <AddMenuItemInOrder mealId={props.selectedMeal.id} onAddItemToOrder={props.onAddItemToOrder}  />
                        }

                    </div>
                </div>
            </div>
            <br/>
            <br/>
            <br/>
            <div className={"row"}>
                <h1 className={"title text-center mt-3 mb-5"}>Check out our most ordered meals</h1>
                <div className={"row"}>
                    {props.topOrderedMeals.map((term)=>{
                        return(
                            <div className={"col-md-3 mostOrderedMeals"}>
                                <MenuItem meal={term} onDelete={props.onDeleteMeal} onMealClick={props.onMealClick} onAddItemToOrder={props.onAddItemToOrder}/>
                            </div>
                        )
                    })}
                </div>
            </div>
        </div>
    )
}

export default mealDetails;