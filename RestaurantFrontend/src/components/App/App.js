import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Route} from 'react-router-dom';
import AuthenticationService from '../../services/AuthenticationService';
import MealCategoriesService from "../../services/MealCategoriesService";
import IngredientService from "../../services/IngredientService";
import MenuService from "../../services/MenuService";
import OrderService from "../../services/OrderService";
import DeliveryService from "../../services/DeliveryService";
import Header from "../Header/header";
import Footer from "../Footer/footer";
import Home from "../Home/home";
import Login from '../Login/login';
import Register from "../Register/register";
import MealCategory from "../MealCategory/mealCategoryList";
import MealCategoryAdd from "../MealCategory/mealCategoryAdd";
import MealCategoryEdit from "../MealCategory/mealCategoryEdit";
import IngredientEdit from "../Ingredients/IngredientEdit";
import IngredientAdd from "../Ingredients/IngredientAdd";
import Ingredient from "../Ingredients/IngredientList";
import Menu from "../Menu/MenuList";
import MealAdd from "../Menu/MealAdd";
import MealDetails from "../Menu/MealDetails";
import MealEdit from "../Menu/MealEdit";
import ShoppingCart from "../ShoppingCart/ShoppingCart";
import CheckOutOrder from "../ShoppingCart/CheckOutOrder";
import OrderList from "../Orders/OrderList";
import DeliveriesList from "../Deliveries/DeliveriesList";
import Profile from "../User/Profile";
import Unauthorized from "../Exceptions/Unauthorized";

class App extends Component{

    constructor(props) {
        super(props);
        this.state={
            loggedInUser:{},
            userInfo:{},
            mealCategories:[],
            selectedCategory:{},
            ingredients:[],
            selectedIngredient:{},
            meals:[],
            filteredMeals:[],
            selectedMeal:{},
            orders:[],
            processingOrders:[],
            deliveries:[],
            selectedDelivery:{},
            selectedOrder:{},
            top5Meals:[],
            orderItems:[]
        }
    }
    render() {
        return(
            <Router>
                <Header username={this.state.loggedInUser.username} onLogoutUser={this.logoutUser}
                        onShoppingCartClick={this.getDetailsForOrder}  />
                <main>
                    <div>
                        <Route path={"/"} exact render={()=>
                            <Home  mealCategories={this.state.mealCategories} onFilterByCategory={this.loadMealsFromMealCategory} />}/>
                        <Route path={"/login"} exact render={()=>
                            <Login onLoginUser={this.loginUser}/> }  />
                        <Route path={"/register"} exact render={()=>
                            <Register onRegisterUser={this.registerUser}/>  } />
                        <Route path={"/profile"} exact render={()=>
                            <Profile user={this.state.userInfo}  onLeaveReview={this.leaveReview} />  }  />

                        <Route path={"/mealCategories/add"} exact render={()=>
                            <MealCategoryAdd onAddMealCategory={this.addMealCategory} />   } />
                        <Route path={"/mealCategories/edit/:id"} exact render={()=>
                            <MealCategoryEdit onEditMealCategory={this.editMealCategory} selectedCategory={this.state.selectedCategory} /> } />
                        <Route path={"/mealCategories"} exact render={()=>
                               <MealCategory mealCategories={this.state.mealCategories}
                                             onEditMealCategory={this.getMealCategory}
                                             onDeleteMealCategory={this.deleteMealCategory}/>  } />

                        <Route path={"/ingredients/add"} exact render={()=>
                            <IngredientAdd onAddIngredient={this.addIngredient} />     } />
                        <Route path={"/ingredients/edit/:id"} exact render={()=>
                            <IngredientEdit onEditIngredient={this.editIngredient} selectedIngredient={this.state.selectedIngredient} /> } />
                        <Route path={"/ingredients"} exact render={()=>
                            <Ingredient ingredients={this.state.ingredients}
                                        onEditIngredient={this.getIngredient}
                                        onDeleteIngredient={this.deleteIngredient}/>  } />

                        <Route path={"/menu/mealCategory/:id"} exact render={()=>
                               <Menu meals={this.state.filteredMeals}
                                     mealCategories={this.state.mealCategories}
                                     onDeleteMeal={this.deleteMealFromMenu}
                                     onMealClick={this.getMealDetails}
                                     onFilterByCategory={this.loadMealsFromMealCategory}
                                     onAddItemToOrder={this.addOrderItemInOrder} />   }  />
                        <Route path={"/meal/add"} exact render={()=>
                            <MealAdd onAddMeal={this.addNewMealToMenu}
                                     ingredients={this.state.ingredients}
                                     mealCategories={this.state.mealCategories}/> } />
                        <Route path={"/meal/edit/:id"} exact render={()=>
                            <MealEdit onEditMeal={this.editMealFromMenu}
                                     ingredients={this.state.ingredients}
                                     mealCategories={this.state.mealCategories}
                                     selectedMeal={this.state.selectedMeal}/> } />
                        <Route path={"/meal/details/:id"} exact render={()=>
                            <MealDetails selectedMeal={this.state.selectedMeal}
                                         ingredients={this.state.ingredients}
                                         topOrderedMeals={this.state.top5Meals}
                                         onDeleteMeal={this.deleteMealFromMenu}
                                         onMealClick={this.getMealDetails}
                                         onAddItemToOrder={this.addOrderItemInOrder}
                                      /> } />
                        <Route path={"/menu"} exact render={()=>
                            <Menu meals={this.state.meals}
                                  mealCategories={this.state.mealCategories}
                                  onDeleteMeal={this.deleteMealFromMenu}
                                  onMealClick={this.getMealDetails}
                                  onFilterByCategory={this.loadMealsFromMealCategory}
                                  onAddItemToOrder={this.addOrderItemInOrder}  /> } />

                        <Route path={"/shoppingCart"} exact render={()=>
                            <ShoppingCart orderedItems={this.state.orderItems}
                                          orderId={this.state.selectedOrder.id}
                                          username={this.state.loggedInUser.username}
                                          onPlusOrderItemFromOrder={this.plusOrderItemQuantity}
                                          onMinusOrderItemFromOrder={this.minusOrderItemQuantity}
                                          onDeleteItemFromOrder={this.deleteOrderItemFromOrder}/>
                        } />

                        <Route path={"/checkOut"} exact render={()=>
                            <CheckOutOrder orderId={this.state.selectedOrder.id}
                                           orderedItems={this.state.orderItems}
                                           username={this.state.loggedInUser.username}
                                           payForOrder={this.payForOrder}
                                           cancelOrder={this.cancelOrder}  />
                        }/>

                        <Route path={"/orders/processing"} exact render={()=>
                            <OrderList orders={this.state.processingOrders}
                                       onReadyForDelivery={this.createNewDelivery}
                                        />
                        }/>
                        <Route path={"/deliveries"} exact render={()=>
                            <DeliveriesList deliveries={this.state.deliveries}
                                            onDelivered={this.finishDelivery} />
                        }/>

                        <Route path={'/unauthorized'} exact render={()=>
                            <Unauthorized /> } />

                    </div>
                </main>
                <Footer/>
            </Router>
        )
    }

    componentDidMount() {
        //localStorage.setItem("userRole", "ROLE_ADMIN")
        this.loadMealCategories();
        this.loadIngredients();
        this.loadMealsForMenu();
        this.getTop5OrderedMeals();
        this.getAllProcessingOrders();
        this.getRemainingDeliveriesForToday();

        const currentUser = AuthenticationService.getCurrentUser();
        if (currentUser)
        {
            this.setState({ loggedInUser: currentUser })
            this.getOrderItemsForUser(currentUser.username);
            this.loadAllOrdersForUser(currentUser.username);
            this.getInfoAboutUser(currentUser.username);
            //console.log(currentUser)
            //console.log(localStorage.getItem("userRole"))
        }

        const mealCategoryId=JSON.parse(localStorage.getItem("selectedCategoryId"))
        mealCategoryId !== null ? this.loadMealsFromMealCategory(mealCategoryId) : this.loadMealsForMenu();

        const mealId=JSON.parse(localStorage.getItem("selectedMealId"))
        mealId !==null ? this.getMealDetails(mealId) : this.loadMealsForMenu();

        const orderId=JSON.parse(localStorage.getItem("selectedOrderId"));
        orderId !==null ? this.getDetailsForOrder(orderId)  : this.loadAllOrders();

    }


    loginUser=(username,password)=>{
        AuthenticationService.loginUser(username,password)
            .then(()=>{
                this.setState({
                    loggedInUser:AuthenticationService.getCurrentUser()
                })
                localStorage.removeItem("loginError")
                const currentUser = AuthenticationService.getCurrentUser();
                localStorage.setItem("userRole",currentUser.role);
                //console.log(localStorage.getItem("userRole"))
                this.getOrderItemsForUser(currentUser.username);
                //this.getActiveOrderForUser(currentUser.username);
            })

    }
    registerUser=(username, password,repeatedPassword,name,surname,phoneNumber,address)=>{
        AuthenticationService.registerUser(username, password,repeatedPassword,name,surname,phoneNumber,address)
            .then(()=>{
                this.loadMealCategories();
            })
            localStorage.removeItem("passwordDoNotMatch");
            localStorage.removeItem("userExists");
    }
    logoutUser=()=>{
        AuthenticationService.logout();
        localStorage.removeItem("userRole");
        //console.log('User is logged out');
        //window.location.href="http://localhost:3000/"
    }
    getInfoAboutUser=(username)=>{
        AuthenticationService.getInfoAboutUser(username)
            .then((data)=>{
                this.setState({
                    userInfo:data.data
                })
            })
    }
    leaveReview=(stars,description,username)=>{
        AuthenticationService.leaveAReview(stars,description,username)
            .then(()=>{
                this.getInfoAboutUser(username);
            })
    }

    loadMealCategories=()=>{
        MealCategoriesService.getAllMealCategories()
            .then((data)=>{
                this.setState({
                    mealCategories:data.data
                })
            })
    }
    getMealCategory=(id)=>{
        MealCategoriesService.getMealCategoryDetails(id)
            .then((data)=>{
                this.setState({
                    selectedCategory:data.data
                })
            })

    }
    addMealCategory=(name,imageUrl)=>{
        MealCategoriesService.addNewMealCategory(name,imageUrl)
            .then(()=>{
                this.loadMealCategories();
            })
    }
    editMealCategory=(id,name,imageUrl)=>{
        MealCategoriesService.editMealCategory(id,name,imageUrl)
            .then(()=>{
                this.loadMealCategories();
            })
    }
    deleteMealCategory=(id)=>{
        MealCategoriesService.deleteMealCategory(id)
            .then(()=>{
                this.loadMealCategories();
            })
    }

    loadIngredients=()=>{
        IngredientService.getAllIngredients()
            .then((data)=>{
                this.setState({
                    ingredients: data.data
                })
            })
    }
    getIngredient=(id)=>{
        IngredientService.getIngredientById(id)
            .then((data)=>{
                this.setState({
                    selectedIngredient:data.data
                })
            })
    }
    addIngredient=(name,quantity)=>{
        IngredientService.addNewIngredient(name,quantity)
            .then(()=>{
                this.loadIngredients();
            })
    }
    editIngredient=(id,name,quantity)=>{
        IngredientService.editIngredient(id,name,quantity)
            .then(()=>{
                this.loadIngredients();
            })
    }
    deleteIngredient=(id)=>{
        IngredientService.deleteIngredient(id)
            .then(()=>{
                this.loadIngredients();
            })
    }

    loadMealsForMenu=()=>{
        MenuService.getMeals()
            .then((data)=>{
                this.setState({
                    meals: data.data
                })
            })
    }
    loadMealsFromMealCategory=(id)=>{
        MenuService.getMealsFromCategory(id)
            .then((data)=>{
                this.setState({
                    filteredMeals:data.data
                })
                localStorage.setItem("selectedCategoryId",id)
            })
    }
    getMealDetails=(id)=>{
        MenuService.getDetailsForMeal(id)
            .then((data)=>{
                this.setState({
                    selectedMeal:data.data
                })
                localStorage.setItem("selectedMealId",id)
            })
    }
    addNewMealToMenu=(name,description,price,mealCategory,imageUrl,ingredientsForMeal)=>{
        MenuService.addNewMealToMenu(name,description,price,mealCategory,imageUrl,ingredientsForMeal)
            .then(()=>{
                this.loadMealsForMenu();
            })
    }
    editMealFromMenu=(id,name,description,price,mealCategory,imageUrl,ingredientsForMeal)=>{
        MenuService.updateMealFromMenu(id,name,description,price,mealCategory,imageUrl,ingredientsForMeal)
            .then(()=>{
                this.loadMealsForMenu();
            })
    }
    deleteMealFromMenu=(id)=>{
        MenuService.deleteMeal(id)
            .then(()=>{
                this.loadMealsForMenu();
            })
    }

    loadAllOrders=()=>{
        OrderService.getAllOrders()
            .then((data)=>{
                this.setState({
                    orders: data.data
                })
            })
    }
    loadAllOrdersForUser=(username)=>{
        OrderService.getAllOrdersForUser(username)
            .then((data)=>{
                this.setState({
                    orders:data.data
                })
            })
    }
    getAllProcessingOrders=()=>{
        OrderService.getAllProcessingOrders()
            .then((data)=>{
                this.setState({
                    processingOrders:data.data
                })
            })
    }
    getAllDeliveringOrders=()=>{
        OrderService.getAllDeliveringOrders()
            .then((data)=>{
                this.setState({
                    deliveringOrders:data.data
                })
            })
    }
    payForOrder=(id)=>{
        OrderService.payForOrder(id)
            .then(()=>{
                this.loadAllOrdersForUser();
            })
    }
    cancelOrder=(username)=>{
        OrderService.cancelOrder(username)
            .then(()=>{
                this.loadAllOrdersForUser();
            })
    }
    getDetailsForOrder=(id)=>{
        OrderService.getDetailsForOrder(id)
            .then((data)=>{
                this.setState({
                    selectedOrder : data.data
                })
                localStorage.setItem("selectedOrderId",id);
            })
    }
    getActiveOrderForUser=(username)=>{
        OrderService.getActiveOrderForUser(username)
            .then((data)=>{
                this.setState({
                    selectedOrder : data.data
                })
            })
    }
    getTop5OrderedMeals=()=>{
        OrderService.getTop5OrderedMeals()
            .then((data)=>{
                this.setState({
                    top5Meals:data.data
                })
            })
    }
    addOrderItemInOrder=(quantity,mealId)=>{
        OrderService.addOrderItemInOrder(quantity,mealId,this.state.loggedInUser.username)
            .then(()=>{
                this.getOrderItemsForUser(this.state.loggedInUser.username);
            })
    }
    plusOrderItemQuantity=(id)=>{
        OrderService.plusOrderItemQuantity(id)
            .then(()=>{
                this.getOrderItemsForUser(this.state.loggedInUser.username);
                this.getActiveOrderForUser(this.state.loggedInUser.username);
            })
    }
    minusOrderItemQuantity=(id)=>{
        OrderService.minusOrderItemQuantity(id)
            .then(()=>{
                this.getOrderItemsForUser(this.state.loggedInUser.username);
                this.getActiveOrderForUser(this.state.loggedInUser.username);
            })
    }
    deleteOrderItemFromOrder=(id)=>{
        OrderService.deleteOrderItemFromOrder(id)
            .then(()=>{
                this.getOrderItemsForUser(this.state.loggedInUser.username);
                this.getActiveOrderForUser(this.state.loggedInUser.username);
            })
    }
    getOrderItemsForUser=(username)=>{
        OrderService.getOrderItemsForUser(username)
            .then((data)=>{
                this.setState({
                    orderItems:data.data
                })
            })
    }
    createNewDelivery=(address,orderId)=>{
        DeliveryService.createNewDelivery(address,orderId)
            .then(()=>{
                this.getAllProcessingOrders();
            })
    }
    getRemainingDeliveriesForToday=()=>{
        DeliveryService.getRemainingDeliveriesForToday()
            .then((data)=>{
                this.setState({
                    deliveries:data.data
                })
                // console.log("taken deliveries")
                // console.log(this.state.deliveries.length)
                localStorage.removeItem("selectedDeliveryId")
            })
    }
    finishDelivery=(id)=> {
        DeliveryService.finishedDelivery(id)
            .then(()=>{
                this.getRemainingDeliveriesForToday();
            })
    }
}


export default App;
