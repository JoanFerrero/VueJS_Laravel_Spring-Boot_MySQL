<?php

namespace App\Http\Resources;

use Illuminate\Http\Request;
use Illuminate\Http\Resources\Json\JsonResource;

class MesaResource extends JsonResource
{
    /**
     * Transform the resource into an array.
     *
     * @return array<string, mixed>
     */
    public function toArray(Request $request)
    {
        return [
            'id' => $this->id,
            'name_mesa' => $this->name_mesa,
            'capacity' => $this->capacity,
            'is_active' => $this->is_active,
            'photo' => $this->photo,
        ];
    }
}
