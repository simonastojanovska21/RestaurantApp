import React from "react";
import {Redirect, useHistory} from "react-router-dom";


const MealEdit=(props)=>{

    const history = useHistory();

    if (localStorage.getItem("userRole")===null ||  !localStorage.getItem("userRole").endsWith("ADMIN"))
    {
        console.log(localStorage.getItem("userRole") !== "ROLE_ADMIN")
        return (
            <Redirect to={"/unauthorized"} />
        )
    }
    // eslint-disable-next-line react-hooks/rules-of-hooks
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
            [e.target.name] : formData.ingredientsForMeal.push(parseInt(e.target.value.trim())),

        })
        //filterIngredientsArray();
        //console.log(formData.ingredientsForMeal)

    }

    const onFormSubmit=(e)=>{
        e.preventDefault();
        const name=formData.name !=="" ? formData.name : props.selectedMeal.name;
        const description=formData.description !=="" ? formData.description : props.selectedMeal.description;
        const price=formData.price !==0.0 ? formData.price : props.selectedMeal.price;
        const mealCategory=formData.mealCategory !==1 ? formData.mealCategory : props.selectedMeal.mealCategory.id;
        const imageUrl=formData.imageUrl !=="" ? formData.imageUrl : props.selectedMeal.imageUrl;
        const ingredientsForMeal=formData.ingredientsForMeal;

        //console.log(ingredientsForMeal)

        props.onEditMeal(props.selectedMeal.id,name,description,price,mealCategory,imageUrl,ingredientsForMeal);
        history.push("/menu");
    }

    return(
        <div className={"container mt-5 mb-5"}>
            <div className={"centeredForms"}>
                <div className={"ps-3 pe-3"}>
                    <h1 className={"title text-center"}>Edit meal</h1>
                    <hr/>
                    <form id={"editMeal"} className={"row g-3"} onSubmit={onFormSubmit}>

                        <div className="col-md-12">
                            <label htmlFor="name" className="form-label">Meal name</label>
                            <input type="text" className="form-control" id="editMealName" name="name"
                                   placeholder={props.selectedMeal.name}
                                   onChange={handleChange}
                                   />

                        </div>

                        <div className="col-md-12">
                            <label htmlFor="description" className="form-label">Meal description</label>
                            <input type="text" className="form-control" id="editMealDescription" name="description"
                                   placeholder={props.selectedMeal.description}
                                   onChange={handleChange}
                                   />

                        </div>

                        <div className="col-md-12">
                            <label htmlFor="price" className="form-label">Meal price</label>
                            <input type="text" className="form-control" id="editMealPrice" name="price"
                                   placeholder={props.selectedMeal.price}
                                   onChange={handleChange}
                                   />

                        </div>

                        <div className="col-md-12">
                            <label htmlFor="mealCategory" className="form-label">Meal category</label>
                            <select name="mealCategory" id={"editMealMealCategory"} className="form-control" onChange={handleChange}>
                                {props.mealCategories.map((term)=> {
                                        if (props.selectedMeal.mealCategory !== undefined && props.selectedMeal.mealCategory.id === term.id )
                                            return <option selected={props.selectedMeal.mealCategory.id}
                                                           value={term.id}>{term.name}</option>
                                        else
                                            return <option value={term.id}>{term.name}</option>
                                    }
                                )}
                            </select>
                        </div>

                        <div className="col-md-12">
                            <label htmlFor="imageUrl" className="form-label">Meal  image url</label>
                            <input type="text" className="form-control" id="editMealImageUrl" name="imageUrl"
                                   placeholder={props.selectedMeal.imageUrl}
                                   onChange={handleChange}
                            />

                        </div>

                        <div className={"col-md-12"}>
                            <ul className={"list-unstyled row"} id={"editIngredientsForMeal"} name="ingredientsForMeal" onChange={handleIngredientChange}>
                                {props.ingredients.map((term)=>{

                                    if(props.selectedMeal.ingredientsForMeal !==undefined && props.selectedMeal.ingredientsForMeal.map(each=>each.id).includes(term.id))
                                        return(
                                            <li className={"col-3"} value={term.id} key={ term.id }>
                                                <input checked={true} className={"me-2 ingredient-"+term.id} value={term.id} type="checkbox"/>
                                                <span>{ term.name }</span>
                                            </li>
                                        )
                                    else
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
                            <button id="editMealSubmit" type="submit" className="btn btn-block text-white" style={{backgroundColor: '#451F16'}}>
                                Submit
                            </button>
                        </div>

                    </form>

                </div>
            </div>
        </div>
    )
}

export default MealEdit;