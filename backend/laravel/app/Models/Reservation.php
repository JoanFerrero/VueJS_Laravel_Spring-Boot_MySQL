<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsToMany;

class Reservation extends Model
{
    use HasFactory;

    protected $fillable = [
        'mesa_id',
        'user_id',
        'accepted',
    ];

    public function user(): BelongsToMany
    {
        return $this->belongsToMany(User::class, 'mesas_users', 'user_id', 'user_id');
    }

    public function mesa(): BelongsToMany
    {
        return $this->belongsToMany(Mesa::class, 'mesas_users', 'mesa_id', 'mesa_id');
    } 
}
