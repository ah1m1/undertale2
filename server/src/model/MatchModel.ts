import {Schema, model} from 'mongoose'

const matchModel = new Schema({
    matchId: {
        type: String,
        required: true
    },
    data: {
        type: Object,
        required: true
    }
})

export const Match = model('match', matchModel)