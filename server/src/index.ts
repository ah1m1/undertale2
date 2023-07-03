import express from 'express'
import {defaultRouter} from "./routes/DefaultRouter";
import {gameRouter} from "./routes/GameRouter";
import mongoose from "mongoose";
import bodyParser from "body-parser";

const app = express()
const port = 25601

app.use(bodyParser.json())

app.listen(port, () => {
    console.log(`Server listening http://localhost:${port}`)

    mongoose.connect(`mongodb://${mongodb}`, () => {
        console.log(`Connected to database`)
    })

    defaultRouter(app)
    gameRouter(app)
})