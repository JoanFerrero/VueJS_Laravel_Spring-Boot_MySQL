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
        return $this->belongsTo(User::class);
    }

    public function mesa(): BelongsToMany
    {
        return $this->belongsTo(Mesa::class);
    }
}
