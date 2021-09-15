import React from "react";
import {Link, Redirect, useHistory} from "react-router-dom"

const Profile=(props)=>{

    const history = useHistory();
    let leftReview=false;
    const [formData, updateFormData] = React.useState({
        stars:1,
        description:"",
        username:"",
    })


    const handleChange=(e)=>{
        updateFormData({
            ...formData,
            [e.target.name] : e.target.value
        })
    }

    const onFormSubmit=(e)=>{
        e.preventDefault();
        const stars=formData.stars;
        const description=formData.description;

        props.onLeaveReview(stars,description,props.user.username);

        //handleDisplay()
    }
    /* istanbul ignore next */
    const handleDisplay=()=>{

        document.getElementById("leaveReview").style.display='none'
        document.getElementById("leftReview").style.display='block';
    }

    /* istanbul ignore else */
    if (localStorage.getItem("user")===null )
    {
        return (
            <Redirect to={"/unauthorized"} />
        )
    }
    return (
        <div className="container mt-5 mb-5">
            <div className="row">
                <div className="col-4">
                    <h3 className="title">User profile</h3>
                    <hr/>
                        <br/>
                            <div className="shadow p-3 mb-5 bg-white rounded border">
                                <span className={"userName"}>{props.user.name}</span> <br/>
                                <span className={"userSurname"}>{props.user.surname}</span><br/>
                                <span className={"userAddress"}>{props.user.address}</span><br/>
                                <span className={"userPhoneNumber"}>{props.user.phoneNumber}</span>
                            </div>
                    { /* istanbul ignore next */ localStorage.getItem("userRole") !== null &&
                    localStorage.getItem("userRole").endsWith("ADMIN") &&
                        <div className={"mt-5 row"}>
                            <Link className={"btn w-100 fw-bold"} id={"mealCategoriesButton"} to={"/mealCategories"}
                                  style={{backgroundColor: '#bb9c57', color: '#004332'}} >Meal categories</Link>

                            <Link className={"btn mt-3 w-100 fw-bold"} id={"ingredientsButton"} to={"/ingredients"}
                                  style={{backgroundColor: '#bb9c57', color: '#004332'}}>Ingredients</Link>
                        </div>
                    }
                </div>

                <div className="col-2">

                </div>

                { !leftReview &&
                <div className="col-6" id={"leaveReview"}>
                    <form id={"reviewForm"} className={"row g-3"} onSubmit={onFormSubmit}>
                        <h2 className="title">Leave us a review</h2>
                        <hr/>
                        <br/>
                        <div className="form-group">
                            <ul className="rate-area">
                                <input type="checkbox" id={"fiveStar"} name="stars" value="5"
                                       onChange={handleChange}/>
                                <label htmlFor="5-star" title="Amazing">5 stars</label>

                                <input type="checkbox" id="4-star" name="stars" value="4"
                                       onChange={handleChange}/>
                                <label htmlFor="4-star" title="Good">4 stars</label>

                                <input type="checkbox" id="3-star" name="stars" value="3"
                                       onChange={handleChange}/>
                                <label htmlFor="3-star" title="Average">3 stars</label>

                                <input type="checkbox" id="2-star" name="stars" value="2"
                                       onChange={handleChange}/>
                                <label htmlFor="2-star" title="Not Good">2 stars</label>

                                <input type="checkbox" id="1-star" name="stars" value="1"
                                       onChange={handleChange}/>
                                <label htmlFor="1-star" title="Bad">1 star</label>
                            </ul>
                        </div>

                        <div className="col-md-12">
                            <label htmlFor="username" className="form-label">Description</label>
                            <div className="input-group has-validation">
                                <span className="input-group-text" id="inputGroupEmail"><i className="fa fa-pencil"/></span>
                                <textarea name="description" className="form-control" id="review" rows="3" onChange={handleChange}/>
                            </div>
                        </div>

                        <div className={"d-grid gap-2 col-md-12 mt-4"}>
                            <button id="submit" type="submit" className="btn btn-block" style={{backgroundColor: '#004332'}}>
                                <span className={"goldText"}>Submit</span>
                            </button>
                        </div>


                    </form>
                </div>
                }

                <div className={"col-6"} id={"leftReview"} style={{display:'none'}}>
                    <h1 className={"greenText"}>Thank you for leaving us a review</h1>
                </div>

            </div>
        </div>
    )
}

export default Profile;