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
        $img = ['https://www.fcmmobiliario.com/blog/wp-content/uploads/2019/04/mesa-cuatro.jpg'];

        return [
            'name_mesa'  => $this->faker->unique()->word(),
            'capacity' => $this->faker->numberBetween(1, 15),
            'is_active' => $this->faker->boolean(),
            'photo' => $img[0],
        ];
    }
}
