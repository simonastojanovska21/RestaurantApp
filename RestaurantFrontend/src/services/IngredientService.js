import axios from "../custom-axios/axios";
/* istanbul ignore next */
const IngredientService={
    getAllIngredients:()=>{
        return axios.get("/api/ingredients")
    },
    getIngredientById:(id)=>{
        return axios.get(`/api/ingredients/${id}`)
    },
    addNewIngredient:(name, quantity)=>{
        return axios.post("/api/ingredients/add",{
            "name":name,
            "quantity":quantity
        })
    },
    editIngredient:(id,name,quantity)=>{
        return axios.put(`/api/ingredients/edit/${id}`,{
            "id":id,
            "name":name,
            "quantity":quantity
        })
    },
    deleteIngredient:(id)=>{
        return axios.delete(`/api/ingredients/delete/${id}`)
    }
}

export default IngredientService;