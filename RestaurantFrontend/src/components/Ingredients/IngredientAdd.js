import React from "react";
import {Redirect, useHistory} from 'react-router-dom';

const IngredientAdd=(props)=>{

    const history = useHistory();

    const [formData, updateFormData] = React.useState({
        name:"",
        quantity:""
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
        const quantity=formData.quantity;

        props.onAddIngredient(name,quantity);
        history.push("/ingredients");
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
                    <h1 className={"title text-center"}>Add new ingredient</h1>
                    <hr/>
                    <form id={"submitIngredient"} className={"row g-3"} onSubmit={onFormSubmit}>

                        <div className="col-md-12">
                            <label htmlFor="name" className="form-label">Ingredient name</label>
                            <input type="text" className="form-control" id="ingredientName" name="name"
                                   placeholder="Enter ingredient name"
                                   onChange={handleChange}
                                   required/>

                        </div>

                        <div className="col-md-12">
                            <label htmlFor="quantity" className="form-label">Ingredient quantity</label>
                            <input type="text" className="form-control" id="ingredientQuantity" name="quantity"
                                   placeholder="Enter quantity of ingredient"
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

export default IngredientAdd