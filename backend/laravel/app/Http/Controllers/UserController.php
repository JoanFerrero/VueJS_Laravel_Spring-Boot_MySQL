<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Http\Resources\UserResource;
use App\Models\User;
use App\Http\Requests\User\StoreUserRequest;
use App\Http\Requests\User\UpdateUserRequest;
use App\Http\Requests\User\LoginUserRequest;

class UserController extends Controller
{
    protected User $user;

    public function __construct(User $user)
    {
        $this->user = $user;
    }
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return UserResource::collection(User::all());
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(StoreUserRequest $request)
    {
        $username_exist = User::where('username', $request->validated()['username'])->get()->count();
        if ($username_exist === 1) {
            return response()->json([
                "Status" => "Username taken"
            ], 400);
        }

        $email_exist = User::where('email', $request->validated()['email'])->get()->count();
        if ($email_exist === 1) {
            return response()->json([
                "Status" => "Email taken"
            ], 400);
        }

        $data = $request->validated();

        $user = $this->user->create($request->validated());
        //return response()->json(["message" => $user], 201);
        return UserResource::make($user);
    }

    /**
     * Display the specified resource.
     */
    public function show(string $id)
    {
        return UserResource::make(User::where('id', $id)->firstOrFail());
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(string $id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(UpdateUserRequest $request, $id)
    {
        $user = User::where('id', $id)->firstOrFail();

        if (isset($request->validated()['username'])) {
            $username_exist = User::where('username', $request->validated()['username'])->get()->count();
            if ($username_exist === 1) {
                return response()->json([
                    "Status" => "Username taken"
                ], 400);
            }
            $user->username = $request->validated()['username'];
        }

        if (isset($request->validated()['email'])) {
            $username_exist = User::where('email', $request->validated()['email'])->get()->count();
            if ($username_exist === 1) {
                return response()->json([
                    "Status" => "Email taken"
                ], 400);
            }
            $user->email = $request->validated()['email'];
        }

        if (isset($request->validated()['password'])) {
            $user->password = bcrypt($request->validated()['password']);
        }


        if (isset($request->validated()['is_active'])) {
            $user->is_active = $request->validated()['is_active'];
        }

        if (isset($request->validated()['photo'])) {
            $user->photo = $request->validated()['photo'];
        }

        return response()->json(["message" => $user], 201);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id)
    {
        $user = User::where('id', $id)->delete();
        if($user == 1) {
            return response()->json([
              "message" => "User deleted"
            ], 202);
        } else {
            return response()->json([
              "message" => "User not found"
            ], 404);
        }
    }
}
