package com.jaewon.toy.user.application.service;

import com.jaewon.toy.board.application.port.out.DeleteBoardPort;
import com.jaewon.toy.reply.application.port.out.DeleteReplyPort;
import com.jaewon.toy.user.application.port.in.DeleteUserByEmailUseCase;
import com.jaewon.toy.user.application.port.out.DeleteUserPort;
import com.jaewon.toy.user.application.port.out.GetUserPort;
import com.jaewon.toy.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class UserDeleteService implements DeleteUserByEmailUseCase {
    private final DeleteBoardPort deleteBoardPort;
    private final DeleteReplyPort deleteReplyPort;
    private final DeleteUserPort deleteUserPort;
    private final GetUserPort getUserPort;

    @Override
    public Mono<Boolean> delete(String email) {
        return getUserPort.getUserByEmail(email)
                .map(User::getId)
                .flatMap(userId -> Mono.when(
                        deleteUserPort.delete(userId),
                        deleteBoardPort.deleteByUserId(userId),
                        deleteReplyPort.deleteByUserId(userId)
                ))
                .thenReturn(true)
                .onErrorReturn(false);
    }
}
