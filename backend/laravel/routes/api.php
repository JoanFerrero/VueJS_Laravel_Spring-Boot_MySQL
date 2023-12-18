<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\MesaController;
use App\Http\Controllers\CategoryController;
use App\Http\Controllers\UserController;
use App\Http\Controllers\ReservationController;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "api" middleware group. Make something great!
|
*/
Route::group(['middleware' => ['admin']], function () {
    Route::resource('mesa', MesaController::class);
    Route::resource('category', CategoryController::class);
    Route::resource('user', UserController::class);  
});

Route::resource('reservation', ReservationController::class);
Route::post('login', [UserController::class, 'login']);
Route::post('logout', [UserController::class, 'logout']);
Route::get('profile', [UserController::class, 'getUserToken']);
Route::get('isAdmin', [UserController::class, 'isAdmin']);
