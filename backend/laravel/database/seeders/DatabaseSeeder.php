<?php

namespace Database\Seeders;

// use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use App\Models\Mesa;
use App\Models\Category;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     */
    public function run(): void
    {
        $categories = Category::factory()->count(2)->create();
        $mesas = Mesa::factory()->count(3)->create();

        foreach ($mesas as $mesa) {
            $mesa->categories()->attach($categories->random(rand(1, 3)));
        } //attach category in mesa
    }
}
