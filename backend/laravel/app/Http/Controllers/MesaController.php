<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Http\Resources\MesaResource;
use App\Http\Requests\StoreMesaRequest;
use App\Http\Requests\UpdateMesaRequest;
use App\Models\Mesa;

class MesaController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    /* return response()->json(["message" => "Hola"], 201); */
    public function index()
    {
        return MesaResource::collection(Mesa::all());
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
    public function store(StoreMesaRequest $request)
    {
        return MesaResource::make(Mesa::create($request->validated()));
    }

    /**
     * Display the specified resource.
     */
    public function show($id)
    {
        return MesaResource::make(Mesa::where('id', $id)->firstOrFail());
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Request $request, $id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(UpdateMesaRequest $request, $id)
    {
        if (Mesa::where('id', $id)->exists()) {
            $mesa = Mesa::find($id);

            $mesa->update($request->validated());
            return MesaResource::make($mesa);
          } else {
            return response()->json([
              "message" => "Mesa not found"
            ], 404);
          } 
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id)
    {
        if(Mesa::where('id', $id)->exists()) {
            $mesa = Mesa::find($id);
            $mesa->delete();
            return response()->json([
              "message" => "Mesa deleted"
            ], 202);
        } else {
            return response()->json([
              "message" => "Mesa not found"
            ], 404);
        }
    }
}
