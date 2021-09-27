package ru.vsu.chess.model.game;

import ru.vsu.chess.model.cell.Cell;
import ru.vsu.chess.model.cell.CellType;
import ru.vsu.chess.model.figure.Figure;
import ru.vsu.chess.model.figure.FigureType;
import ru.vsu.chess.model.player.Player;
import ru.vsu.chess.controller.playercontroller.PlayerController;
import ru.vsu.chess.services.movechecker.MoveValidator;
import java.util.*;
public class Game {
    //Something like board
    private Cell leftUpCell;
    //all players settings
    private final Queue<Player> players;
    private final Map<Player, PlayerController> playerServiceMap;
    private final Map<Player, MoveValidator> playerMoveValidatorMap;
    public final Map<Player, Map<FigureType, String>> visualFigure;
    private Map<Player, List<Figure>> playerFigures;

    //actually for figures
    private Map<CellType, Set<CellType>> galasHomeHelper;
    private Map<Cell, CellType> cells;
    private Map<Cell, Figure> cellFigure;
    private Map<Figure, Cell> figureCell;
    private Map<Figure, Cell> basicFigureCellPosition;
    private Map<Cell, Figure> basicCellFigurePosition;
    private Map<CellType, Map<FigureType, List<Direction>>> figuresDirections;

    //game properties
    private GameStatus status;
    private final List<Step> steps;

    private Game(){
        players = new LinkedList<>();
        steps = new ArrayList<>();
        status = GameStatus.OK;
        playerServiceMap = new HashMap<>();
        playerMoveValidatorMap = new HashMap<>();
        visualFigure = new HashMap<>();
        galasHomeHelper = new HashMap<>();
    }

    public Map<Player, Map<FigureType, String>> getVisualFigure() {
        return visualFigure;
    }

    public Map<CellType, Map<FigureType, List<Direction>>> getFiguresDirections() {
        return figuresDirections;
    }

    public void setFiguresDirections(Map<CellType, Map<FigureType, List<Direction>>> figuresDirections) {
        this.figuresDirections = figuresDirections;
    }

    public Map<CellType, Set<CellType>> getGalasHomeHelper() {
        return galasHomeHelper;
    }

    public void setGalasHomeHelper(Map<CellType, Set<CellType>> galasHomeHelper) {
        this.galasHomeHelper = galasHomeHelper;
    }

    public Map<Player, MoveValidator> getPlayerMoveValidatorMap() {
        return playerMoveValidatorMap;
    }

    public Map<Player, PlayerController> getPlayerServiceMap() {
        return playerServiceMap;
    }

    public Cell getLeftUpCell() {
        return leftUpCell;
    }

    public void setLeftUpCell(Cell leftUpCell) {
        this.leftUpCell = leftUpCell;
    }

    public Map<Figure, Cell> getBasicFigureCellPosition() {
        return basicFigureCellPosition;
    }

    public void setBasicFigureCellPosition(Map<Figure, Cell> basicFigureCellPosition) {
        this.basicFigureCellPosition = basicFigureCellPosition;
    }

    public Map<Cell, Figure> getBasicCellFigurePosition() {
        return basicCellFigurePosition;
    }

    public void setBasicCellFigurePosition(Map<Cell, Figure> basicCellFigurePosition) {
        this.basicCellFigurePosition = basicCellFigurePosition;
    }

    public Map<Cell, CellType> getCells() {
        return cells;
    }

    public void setCells(Map<Cell, CellType> cells) {
        this.cells = cells;
    }

    public static Game createGame(){
        return new Game();
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public Queue<Player> getPlayers() {
        return players;
    }


    public Map<Player, List<Figure>> getPlayerFigures() {
        return playerFigures;
    }

    public void setPlayerFigures(Map<Player, List<Figure>> playerFigures) {
        this.playerFigures = playerFigures;
    }

    public Map<Cell, Figure> getCellFigure() {
        return cellFigure;
    }

    public void setCellFigure(Map<Cell, Figure> cellFigure) {
        this.cellFigure = cellFigure;
    }

    public Map<Figure, Cell> getFigureCell() {
        return figureCell;
    }

    public void setFigureCell(Map<Figure, Cell> figureCell) {
        this.figureCell = figureCell;
    }
}
