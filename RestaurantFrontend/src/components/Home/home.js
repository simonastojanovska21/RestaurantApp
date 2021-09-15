import React from "react";
import MealCarousel from './carousel';
import MealCategory from './mealCategory';

const home=(props)=>{
    return(
        <div className={"greenBackground"}>
            <MealCarousel/>
            <div className={"container mt-5 pb-5 text-center"}>
                <span className={"title"}>Meal categories</span>
                <div id={"homeMealCategory"} className={"row"}>
                    {props.mealCategories.map((term)=>{
                        return(
                            <MealCategory  category={term} onFilterByCategory={props.onFilterByCategory} />
                        )
                    })}
                </div>
            </div>
        </div>
    )
}

export default home;