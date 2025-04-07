def solution(X, Y):
    x_dict = {}  
    y_dict = {}  

    for x, y in zip(X, Y):
        if x in x_dict:
            x_dict[x].append(y)
        else:
            x_dict[x] = [y]

        if y in y_dict:
            y_dict[y].append(x)
        else:
            y_dict[y] = [x]
    if len(x_dict) <= 2 or len(y_dict) <= 2:
        return 0

    count = 0
    for i in x_dict:  
        point_count = x_dict[i]  
        if len(point_count) >= 2:
            for o in range(len(point_count) - 1):
                for t in range(o + 1, len(point_count)):
                    y_sum = point_count[o] + point_count[t]  
                    if y_sum % 2 == 0:
                        y_value = y_sum // 2
                        if y_value in y_dict:  
                            y_point = y_dict[y_value]  
                            if len(y_point) >= 2:  
                                sort_y_point = sorted(y_point)
                                min_num = sort_y_point[0]
                                max_num = sort_y_point[-1]
                                if min_num < i < max_num:  
                                    for j in sort_y_point:
                                        if j < i:
                                            if 2 * i - j in sort_y_point:  
                                                count += 1
                                        else:  
                                            break
    return count
