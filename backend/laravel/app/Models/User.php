<?php

namespace App\Models;

// use Illuminate\Contracts\Auth\MustVerifyEmail;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Notifications\Notifiable;
use Laravel\Sanctum\HasApiTokens;

class User extends Authenticatable
{
    use HasApiTokens, HasFactory, Notifiable;

    /**
     * The attributes that are mass assignable.
     *
     * @var array<int, string>
     */
    protected $fillable = [
        'username',
        'password',
        'email',
        'type',
        'is_active',
        'photo',
    ];

    protected $hidden = [
        'password'
    ];

    public function create($fields)
    {
        return parent::create([
            'username' => $fields['username'],
            'password' => bcrypt($fields['password']),
            'email' => $fields['email'],
            'type' => 'client',
            'is_active' =>  isset($fields['is_active']) ? $fields['is_active'] : true,
            'photo' =>  isset($fields['photo']) ? $fields['photo'] : 'https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Cat03.jpg/1200px-Cat03.jpg',
        ]);
    } //create

    public function getJWTIdentifier()
    {
        return $this->getKey();
    }

    public function getJWTCustomClaims()
    {
        return [];
    }
}
