import React from "react";
import {Link, Redirect} from "react-router-dom";

const MealCategory=(props)=>{

    if (localStorage.getItem("userRole")===null ||  !localStorage.getItem("userRole").endsWith("ADMIN"))
    {
        //console.log(localStorage.getItem("userRole") !== "ROLE_ADMIN")
        return (
            <Redirect to={"/unauthorized"} />
        )
    }
    return(
        <div className={"container mt-5 pb-5 text-center"}>
            <span className={"title"}>Meal categories</span>
            <div className="col mt-3 mb-3">
                <div className="row">
                    <div className="d-grid gap-2 col-sm-12">
                        <Link className={"btn btn-block"} id={"addMealCategory"} style={{backgroundColor:'#004332'}} to={"/mealCategories/add"}>
                            <span className={"goldText fw-bold"}>Add new meal category</span>
                        </Link>
                    </div>
                </div>
            </div>
            <div className={"row"}>
                <div className={"table-responsive"}>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col-md-3"}>Image</th>
                            <th scope={"col-md-3"}>Name</th>
                            <th scope={"col-md-6"}>Edit/Delete</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.mealCategories.map((term)=>{
                            return(
                                <tr className={"mealCategoriesRow"}>
                                    <td>
                                        <img className={"imageInTable mealCategoryImage-"+term.id} src={term.imageUrl} alt={"meal category"}/>
                                    </td>
                                    <td className={"text-center align-middle mealCategoryName-"+term.id}>
                                        <h3>{term.name}</h3>
                                    </td>
                                    <td className={"text-right align-middle"}>
                                        <div className={"d-grid gap-2 col-md-12 "}>
                                            <Link className={"btn btn-sm text-white mealCategoryDelete-"+term.id} style={{backgroundColor: '#451F16'}}
                                                  onClick={() => props.onDeleteMealCategory(term.id)}>
                                                Delete
                                            </Link>

                                            <Link className={"btn btn-sm mealCategoryEdit-"+term.id} style={{backgroundColor: '#bb9c57'}}
                                                  onClick={()=>props.onEditMealCategory(term.id)}
                                                  to={`/mealCategories/edit/${term.id}`}>
                                                <span className={"greenText"}>Edit</span>
                                            </Link>
                                        </div>
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

export default MealCategory;