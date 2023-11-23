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
        $categories = [];
        foreach ($this->categories as $c) {
            $category = [
                "id" => $c->id,
                "name_category" => $c->name_category,
                "photo" => $c->photo
            ];
            array_push($categories, $category);
        }
        
        return [
            'id' => $this->id,
            'name_mesa' => $this->name_mesa,
            'capacity' => $this->capacity,
            'is_active' => $this->is_active,
            'photo' => $this->photo,
            'categories' => $categories,
        ];
    }
}
