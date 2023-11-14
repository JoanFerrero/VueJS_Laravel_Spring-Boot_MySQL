<?php

namespace Database\Factories;

use App\Models\Mesa;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\Mesa>
 */
class MesaFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */

    protected $model = Mesa::class;

    public function definition(): array
    {
        return [
            'nombre' => $this->faker->firstName(),
            'categoria' => $this->faker->lastName(),
            'capacidad' => $this->faker->numberBetween(1, 15)
        ];
    }
}
