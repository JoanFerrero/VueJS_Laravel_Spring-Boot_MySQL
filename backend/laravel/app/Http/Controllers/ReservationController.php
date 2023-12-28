<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Http\Resources\ReservationResource;
use App\Models\Reservation;
use App\Http\Requests\Reservation\UpdateReservationRequest;

class ReservationController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return ReservationResource::collection(Reservation::all());
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
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     */
    public function show(string $id)
    {
        return ReservationResource::make(Reservation::where('id', $id)->firstOrFail());
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
    public function update(UpdateReservationRequest $request, string $id)
    {
        $reservation = Reservation::where('id', $id)->firstOrFail();
        $data = ["accepted" => !$reservation->accepted];
        $update = Reservation::where('id', $id)->update($data);
        if($update == 1) {
            $reservation = ReservationResource::make(Reservation::where('id', $id)->firstOrFail());
            return response()->json([
                $reservation
            ], 200);
        } else {
            return response()->json([
                "Status" => "Update not correct"
            ], 401);
        }
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id)
    {
        $delete = Reservation::where('id', $id)->delete();
        if($delete == 1) {
            return response()->json([
              "message" => "Reservation deleted"
            ], 200);
        } else {
            return response()->json([
              "message" => "Reservation not found"
            ], 401);
        }
    }
}
