import React from "react";
import {Link} from "react-router-dom";
import AddMenuItemInOrder from "../Menu/AddMenuItemInOrder"

const menuItem=(props)=>{
    return(
        <div className={"border-0 gridImageOnHover mt-3 mb-3 menuItems"}>
            <Link className={"btn meal-"+props.meal.id} onClick={()=>props.onMealClick(props.meal.id)}
                  to={`/meal/details/${props.meal.id}`}>
                <img src={props.meal.imageUrl} className="card-img-top gridImages" alt="meal"/>
                <div className="card-body">
                    <h4 className={"goldText menuItemName"}>
                        {props.meal.name}
                    </h4>
                    <h4 className={"goldText"}>
                        ({props.meal.price})
                    </h4>

                </div>
            </Link>

            {localStorage.getItem("user") !== null &&
                <AddMenuItemInOrder mealId={props.meal.id} onAddItemToOrder={props.onAddItemToOrder}/>
            }

            {
                localStorage.getItem("userRole")!==null && localStorage.getItem("userRole").endsWith("ADMIN") &&
                <div className={"row ms-2 me-2"}>
                    <Link className={"btn col me-2 mealEdit-"+props.meal.id} onClick={()=>props.onMealClick(props.meal.id)} style={{backgroundColor: '#bb9c57'}}
                          to={`/meal/edit/${props.meal.id}`}>
                        <span className={"greenText"}>Edit</span>
                    </Link>
                    <Link className={"btn col text-white mealDelete-"+props.meal.id} onClick={()=>props.onDelete(props.meal.id)} style={{backgroundColor: '#451F16'}}>
                        Delete
                    </Link>
                </div>
            }
        </div>
    )
}
export default menuItem;