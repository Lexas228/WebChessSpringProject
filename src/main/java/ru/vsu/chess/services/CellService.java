package ru.vsu.chess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.chess.model.node.NodeCell;
import ru.vsu.chess.repository.CellRepository;

@Service
public class CellService {
    private final CellRepository cellRepository;

    @Autowired
    public CellService(CellRepository cellRepository) {
        this.cellRepository = cellRepository;
    }

    public NodeCell createBoard(){
        NodeCell[][] c = new NodeCell[][];

    }
}
