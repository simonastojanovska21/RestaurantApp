import React from "react";
import {Link, Redirect} from "react-router-dom";

const ingredientList=(props)=>{

    if (localStorage.getItem("userRole")===null ||  !localStorage.getItem("userRole").endsWith("ADMIN"))
    {
        //console.log(localStorage.getItem("userRole") !== "ROLE_ADMIN")
        return (
            <Redirect to={"/unauthorized"} />
        )
    }

return(
        <div className={"container mt-5 pb-5 text-center"} id={"ingredientsList"}>
            <span className={"title"}>List of ingredients</span>
            <div className="col mt-3 mb-3">
                <div className="row">
                    <div className="d-grid gap-2 col-sm-12">
                        <Link className={"btn btn-block"} id={"addIngredient"} style={{backgroundColor:'#004332'}} to={"/ingredients/add"}>
                            <span className={"goldText fw-bold"}>Add new ingredient</span>
                        </Link>
                    </div>
                </div>
            </div>
            <div className={"row"}>
                <div className={"table-responsive"}>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col-md-3"}>Name</th>
                            <th scope={"col-md-3"}>Quantity</th>
                            <th scope={"col-md-6"}>Edit/Delete</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.ingredients.map((term)=>{
                            return(
                                <tr className={"ingredientsRow"}>
                                    <td className={"align-middle ingredientName-"+term.id}>
                                        {term.name}
                                    </td>
                                    <td className={"text-center align-middle ingredientQuantity-"+term.id}>
                                        {term.quantity}
                                    </td>
                                    <td className={"text-right align-middle"}>
                                        <Link className={"btn text-white me-3 ingredientDelete-"+term.id} style={{backgroundColor: '#451F16'}}
                                              onClick={() => props.onDeleteIngredient(term.id)}>
                                            Delete
                                        </Link>

                                        <Link className={"btn ingredientEdit-"+term.id} style={{backgroundColor: '#bb9c57'}}
                                              onClick={()=>props.onEditIngredient(term.id)}
                                              to={`/ingredients/edit/${term.id}`}>
                                            <span className={"greenText"}>Edit</span>
                                        </Link>
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

export default ingredientList;