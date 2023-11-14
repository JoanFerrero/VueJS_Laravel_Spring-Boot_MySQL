<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class StoreMesaRequest extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     */
    public function authorize()
    {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array<string, \Illuminate\Contracts\Validation\ValidationRule|array<mixed>|string>
     */
    public function rules()
    {
        return [
            'nombre' => 'required|string|max:30',
            'categoria' => 'required|string|max:30',
            'capacidad' => 'required|integer|max:30',
        ];
    }
    public function messages()
    {
        return [
            'nombre.required' => 'El :attribute es obligatorio',
            'categoria.required' => 'Añade una :attribute a la mesa',
            'capacidad.required' => 'Añade una :attribute a la mesa',
        ];
    }
   
    public function attributes()
    {
        return [
            'nombre' => 'nombre of mesa',
            'categoria' => 'categoria of mesa',
            'capacidad' => 'capacidad of mesa',
        ];
    }
}
