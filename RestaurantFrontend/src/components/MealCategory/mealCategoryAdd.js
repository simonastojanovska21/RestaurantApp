import React from "react";
import {useHistory} from 'react-router-dom';
import {Redirect} from 'react-router-dom';

const MealCategoryAdd=(props)=>{
    const history = useHistory();

    const [formData, updateFormData] = React.useState({
        name:"",
        imageUrl:""
    })


    const handleChange=(e)=>{
        updateFormData({
            ...formData,
            [e.target.name] : e.target.value.trim()
        })
    }

    const onFormSubmit=(e)=>{
        e.preventDefault();
        const name=formData.name;
        const imageUrl=formData.imageUrl;

        props.onAddMealCategory(name,imageUrl);
        history.push("/mealCategories");
    }

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
                    <h1 className={"title text-center"}>Add new meal category</h1>
                    <hr/>
                    <form id={"mealCategoryAdd"} className={"row g-3"} onSubmit={onFormSubmit}>

                        <div className="col-md-12">
                            <label htmlFor="name" className="form-label">Meal category name</label>
                            <input type="text" className="form-control" id="mealCategoryName" name="name"
                                   placeholder="Enter meal category name"
                                   onChange={handleChange}
                                   required/>

                        </div>

                        <div className="col-md-12">
                            <label htmlFor="imageUrl" className="form-label">Meal category image url</label>
                            <input type="text" className="form-control" id="mealCategoryImageUrl" name="imageUrl"
                                   placeholder="Enter meal category image url"
                                   onChange={handleChange}
                                   required/>

                        </div>

                        <div className={"d-grid gap-2 col-md-12 mt-4"}>
                            <button id="submit" type="submit" className="btn btn-block text-white" style={{backgroundColor: '#451F16'}}>
                               Submit
                            </button>
                        </div>

                    </form>

                </div>
            </div>
        </div>
    )
}

export default MealCategoryAdd;