import React from "react";
import {Redirect, useHistory} from 'react-router-dom';

const MealAdd=(props)=>{

    const history = useHistory();

    const [formData, updateFormData] = React.useState({
        name:"",
        description:"",
        price:0.0,
        mealCategory:1,
        imageUrl:"",
        ingredientsForMeal:[]
    })

    const handleChange=(e)=>{
        updateFormData({
            ...formData,
            [e.target.name] : e.target.value.trim()
        })

    }

    const handleIngredientChange=(e)=>{
        updateFormData({
            ...formData,
            [e.target.name] : formData.ingredientsForMeal.push(parseInt(e.target.value.trim()))
        })
        //console.log(formData.ingredientsForMeal);
    }

    const onFormSubmit=(e)=>{
        e.preventDefault();
        const name=formData.name;
        const description=formData.description;
        const price=formData.price;
        const mealCategory=formData.mealCategory;
        const imageUrl=formData.imageUrl;
        const ingredientsForMeal=formData.ingredientsForMeal;

        //console.log(formData)
        props.onAddMeal(name,description,price,mealCategory,imageUrl,ingredientsForMeal);
        history.push("/menu");
    }

    /* istanbul ignore else */
    if (localStorage.getItem("userRole")===null ||  !localStorage.getItem("userRole").endsWith("ADMIN"))
    {
        //console.log(localStorage.getItem("userRole") !== "ROLE_ADMIN")
        return (
            <Redirect to={"/unauthorized"} />
        )
    }


    return(
        <div className={"container mt-5 mb-5"}>
            <div className={"centeredForms"}>
                <div className={"ps-3 pe-3"}>
                    <h1 className={"title text-center"}>Add new meal</h1>
                    <hr/>
                    <form id={"addMeal"} className={"row g-3"} onSubmit={onFormSubmit}>

                        <div className="col-md-12">
                            <label htmlFor="name" className="form-label">Meal name</label>
                            <input type="text" className="form-control" id="mealName" name="name"
                                   placeholder="Enter meal name"
                                   onChange={handleChange}
                                   required/>

                        </div>

                        <div className="col-md-12">
                            <label htmlFor="description" className="form-label">Meal description</label>
                            <input type="text" className="form-control" id="mealDescription" name="description"
                                   placeholder="Enter meal description"
                                   onChange={handleChange}
                                   required/>

                        </div>

                        <div className="col-md-12">
                            <label htmlFor="price" className="form-label">Meal price</label>
                            <input type="text" className="form-control" id="mealPrice" name="price"
                                   placeholder="Enter meal price"
                                   onChange={handleChange}
                                   required/>

                        </div>

                        <div className="col-md-12">
                            <label htmlFor="mealCategory" className="form-label">Meal category</label>
                            <select name="mealCategory" id={"mealMealCategory"} className="form-control" onChange={handleChange} required>
                                <option>Select meal category</option>
                                {props.mealCategories.map((term)=>
                                    <option value={term.id}>{term.name}</option>
                                )}
                            </select>
                        </div>

                        <div className="col-md-12">
                            <label htmlFor="imageUrl" className="form-label">Meal  image url</label>
                            <input type="text" className="form-control" id="mealImageUrl" name="imageUrl"
                                   placeholder="Enter meal image url"
                                   onChange={handleChange}
                                   required/>

                        </div>

                        <div className={"col-md-12"}>
                            <label htmlFor={"ingredientsForMeal"} className={"form-label"}>Ingredients for meal</label>
                            <ul className={"list-unstyled row"} id={"ingredientsForMeal"} name="ingredientsForMeal" onChange={handleIngredientChange}>
                                {props.ingredients.map((term)=>{
                                    return(
                                        <li className={"col-3"} value={term.id} key={ term.id }>
                                            <input className={"me-2 ingredient-"+term.id} value={term.id} type="checkbox"/>
                                            <span>{ term.name }</span>
                                        </li>
                                    )
                                })}
                            </ul>
                        </div>

                        <div className={"d-grid gap-2 col-md-12 mt-4"}>
                            <button id="addMealSubmit" type="submit" className="btn btn-block text-white" style={{backgroundColor: '#451F16'}}>
                                Submit
                            </button>
                        </div>

                    </form>

                </div>
            </div>
        </div>
    )
}

export default MealAdd;