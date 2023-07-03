import {Express} from "express";
import {Match} from "../model/MatchModel";

export const gameRouter = (app: Express) => {
    app.post('/api/v1/game/:gameId', async (req, res) => {
        const matchId = req.params.gameId
        const data = req.body

        const game = await Match.findOne({matchId: matchId})

        if(!game) {
            const match = new Match({
                matchId: matchId,
                data: data
            })
            await match.save()
        }else {
            game.data = data
            await game.save()
        }

        res.json({
            message: 'match updated'
        })
    })

    app.get('/api/v1/game/:gameId', async (req, res) => {
        const matchId = req.params.gameId
        const game = await Match.findOne({matchId: matchId})

        res.json(game)
    })
}