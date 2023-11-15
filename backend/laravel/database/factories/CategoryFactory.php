<?php

namespace Database\Factories;

use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\Model>
 */
class CategoryFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        $img = ['https://www.fcmmobiliario.com/blog/wp-content/uploads/2019/04/mesa-cuatro.jpg'];
        
        return [
            'name_category' => $this->faker->unique()->word(),
            'photo' => $img[0],
        ];
    }
}
