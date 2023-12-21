<?php

namespace App\Http\Resources;

use Illuminate\Http\Request;
use Illuminate\Http\Resources\Json\JsonResource;
use App\Models\Mesa;
use App\Models\User;

class ReservationResource extends JsonResource
{
    public function toArray(Request $request)
    {        
        return [
            'id' => $this->id,
            'fecha_reserva' => $this->fecha_reserva,
            'hora' => $this->categoria,
            'mesa' => Mesa::where('id', $this->mesa_id)->firstOrFail(),
            'user' => User::where('id', $this->user_id)->firstOrFail(),
            'accepted' => $this->accepted,
        ];
    }
}
