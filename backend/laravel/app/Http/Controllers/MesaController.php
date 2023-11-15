<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Http\Resources\MesaResource;
use App\Http\Requests\Mesa\StoreMesaRequest;
use App\Http\Requests\Mesa\UpdateMesaRequest;
use App\Http\Requests\Mesa\CategoryResource;
use App\Models\Mesa;
use App\Models\Category;
use Illuminate\Support\Facades\Log;

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
        $data = $request->except(['categories']);
        $categories = Category::where('name_category', $request->categories)->get();
        if($categories) {
            $categories_id = [];
            foreach ($categories as $c) {
                array_push($categories_id, $c->id);
            }
            $mesa = Mesa::create($data);
            $mesa->categories()->sync($categories_id);
            return MesaResource::make($mesa);
        } else {
            return response()->json([
                "Status" => "Not found"
            ], 404);
        }
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
        $data = $request->except(['categories']);
        $categories_name = [];
        if ($request->categories !== null) {
            $categories_name = $request->categories;

            $categories = Category::where('name_category', $categories_name)->get();
            $categories_id = [];
            foreach ($categories as $c) {
                array_push($categories_id, $c->id);
            }
        }

        $update = Mesa::where('id', $id)->update($data);

        if ($update == 1) {
            if (count($categories_id) > 0) {
                $mesa = Mesa::where('id', $id)->firstOrFail();
                $mesa->categories()->detach();
                $mesa->categories()->sync($categories_id);
            }

            return response()->json([
                "Message" => "Updated correctly"
            ]);
        } else {
            return response()->json([
                "Status" => "Not found"
            ], 404);
        };
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id)
    {
        $delete = Mesa::where('id', $id)->delete();
        if($delete == 1) {
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
